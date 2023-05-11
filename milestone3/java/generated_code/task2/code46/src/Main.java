import org.junit.jupiter.api.Test;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Predicate;

public class Main {

    @Test
    public void testAny_true() {
        // Arrange
        Observable<String> observable = Observable.just("apple", "banana", "cherry");

        Predicate<String> predicate = s -> s.contains("a");

        // Act
        Single<Boolean> hasStringWithA = observable.any(predicate);

        // Assert
        hasStringWithA.test().assertValue(true);
    }

    @Test
    public void testAny_false() {
        // Arrange
        Observable<String> observable = Observable.just("berry", "chickpea", "date");

        Predicate<String> predicate = s -> s.contains("a");

        // Act
        Single<Boolean> hasStringWithA = observable.any(predicate);

        // Assert
        hasStringWithA.test().assertValue(false);
    }

    @Test
    public void testAny_emptyObservable() {
        // Arrange
        Observable<String> observable = Observable.empty();

        Predicate<String> predicate = s -> s.contains("a");

        // Act
        Single<Boolean> hasStringWithA = observable.any(predicate);

        // Assert
        hasStringWithA.test().assertValue(false);
    }
}