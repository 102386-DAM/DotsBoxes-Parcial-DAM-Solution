package cat.udl.tidic.amd.dotsboxes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import cat.udl.tidic.amd.dotsboxes.databinding.ActivityGameBinding;
import cat.udl.tidic.amd.dotsboxes.viewmodels.GameViewModel;
import cat.udl.tidic.amd.dotsboxes.views.GameView;

public class GameActivity extends AppCompatActivity {

    protected GameView gameView;
    private  GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // @Didac: Aquesta funció va eliminada en el repo del parcial
        initDataBinding();
        gameView = (GameView) findViewById(R.id.gameView);
        gameView.setGameViewModel(gameViewModel);
        //

    }

    // @Didac: Aquesta funció va eliminada en el repo del parcial
    private void initDataBinding() {
        ActivityGameBinding activityGameBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_game);
        gameViewModel = new GameViewModel();
        activityGameBinding.setGameViewModel(gameViewModel);
        // Required to update UI with LiveData
        activityGameBinding.setLifecycleOwner(this);
    }
    //
}