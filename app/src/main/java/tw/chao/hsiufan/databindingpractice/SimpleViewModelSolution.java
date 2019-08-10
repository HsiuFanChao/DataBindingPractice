package tw.chao.hsiufan.databindingpractice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class SimpleViewModelSolution extends ViewModel {

    public enum Popularity {
        NORMAL,
        POPULAR,
        STAR
    }

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> lastName = new MutableLiveData<>();
    public MutableLiveData<Integer> likes = new MutableLiveData<>();

    public LiveData<Popularity> popularity = Transformations.map(likes, likeValue -> {
        if (likeValue > 9) {
            return Popularity.STAR;
        }
        if (likeValue > 4) {
            return Popularity.POPULAR;
        }
        return Popularity.NORMAL;
    });

    public SimpleViewModelSolution() {
        name.setValue("Steven");
        lastName.setValue("Chao");
        likes.setValue(0);
    }

    public void onLike() {
        Integer likeValue = likes.getValue();
        if (likeValue == null) {
            return;
        }
        likes.setValue(likeValue + 1);
    }
}
