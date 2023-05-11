import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.functions.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.*;

public final class ObservableAnySingle<T> extends Single<Boolean> {

    private final ObservableSource<T> source;

    private final Predicate<? super T> predicate;

    private final Function<? super T, ? extends Boolean> mapper;

    private final Function<Scheduler, Scheduler> schedulerFactory;

    public ObservableAnySingle(ObservableSource<T> source,
                               Predicate<? super T> predicate,
                               Function<? super T, ? extends Boolean> mapper,
                               Function<Scheduler, Scheduler> schedulerFactory) {
        this.source = source;
        this.predicate = predicate;
        this.mapper = mapper;
        this.schedulerFactory = schedulerFactory;
    }

    @Override
    protected void subscribeActual(SingleObserver<? super Boolean> observer) {
        // create a subject to emit the filtered elements
        final PublishSubject<T> subject = PublishSubject.create();

        // compose the source with the mapper to emit Boolean values
        Observable<Boolean> mappedSource = RxJavaPlugins.onAssembly(
            source.subscribeOn(schedulerFactory.apply(Schedulers.computation()))
                    .observeOn(schedulerFactory.apply(Schedulers.io()))
                    .filter(predicate)
                    .map(mapper)
        );

        // subscribe to the mapped source with a maybe
        mappedSource.subscribe(new MaybeObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                // do nothing on subscribe
            }

            @Override
            public void onSuccess(Boolean result) {
                // emit the result if true, complete otherwise
                if (result) {
                    observer.onSuccess(true);
                } else {
                    observer.onSuccess(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onSuccess(false);
            }
        });

        // subscribe to the source with the subject
        subject.subscribe(mappedSource);

        // commence streaming
        RxJavaPlugins.onAssembly(source).subscribe(subject);
    }
}