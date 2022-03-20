package cat.udl.tidic.amd.dotsboxes.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.tidic.amd.dotsboxes.models.Game;

// @Didac: Aquesta classe va buida sense continguts
public class GameViewModel extends ViewModel {

    public MutableLiveData<Game> currentGame;

    public GameViewModel() {
        currentGame = new MutableLiveData<>();
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame.setValue(currentGame);
    }

}
