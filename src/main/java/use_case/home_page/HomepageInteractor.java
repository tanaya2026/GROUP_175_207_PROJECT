package use_case.home_page;

/**
 * The HomePage Interactor.
 */
public class HomepageInteractor implements HomepageInputBoundary {
    private final HomepageOutputBoundary homepagePresenter;

    public HomepageInteractor(HomepageOutputBoundary homepageOutputBoundary) {
        this.homepagePresenter = homepageOutputBoundary;
    }

    @Override
    public void execute(HomepageInputData homepageInputData) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData();
        homepagePresenter.prepareSuccessView(homepageOutputData);
    }

}
