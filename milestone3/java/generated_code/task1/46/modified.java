@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
// modified method signature to accept a scheduler factory function
public final Single<Boolean> any(Predicate<? super T> predicate, Function<Scheduler, Scheduler> schedulerFactory) {
    // check if the predicate is null
    ObjectHelper.requireNonNull(predicate, "predicate is null");

    // create an asynchronous Observable source
    Observable<T> source = subscribeOn(schedulerFactory) // move upstream operations to a separate thread
            .observeOn(schedulerFactory) // move downstream operations to the same separate thread
            .filter(predicate); // filter the elements of the source based on the predicate

    // create a Single that emits true if any element satisfies the predicate, false otherwise
    return RxJavaPlugins.onAssembly(source.isEmpty().map(isEmpty -> !isEmpty));
}