package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                           SignupViewModel signupViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the signup view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        // 1. get the LoggedInState out of the appropriate View Model,
        // 2. set the username in the state to the empty string
        // 3. firePropertyChanged so that the View that is listening is updated.
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("");
        loggedInViewModel.firePropertyChange();

        // 1. get the SignupState out of the appropriate View Model,
        // 2. set the fields in the state to empty strings to clear the form,
        // 3. firePropertyChanged so that the View that is listening is updated.
        SignupState signupState = signupViewModel.getState();
        signupState.setUsername("");
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        signupState.setUsernameError(null);
        signupState.setPasswordError(null);
        signupState.setRepeatPasswordError(null);
        signupViewModel.firePropertyChange();


        // This code tells the View Manager to switch to the SignupView.
        this.viewManagerModel.setState(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}
