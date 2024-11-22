package app;

import data_access.DataAccessObject;
//import use_case.FormTeamUseCase;
//import use_case.GetAverageGradeUseCase;
//import use_case.GetGradeUseCase;
//import use_case.GetTopGradeUseCase;
//import use_case.JoinTeamUseCase;
//import use_case.LeaveTeamUseCase;
import use_case.CreateResourceUseCase;

/**
 * Config class to provide use cases with the necessary dependencies.
 */

public class Config {
    private final DataAccessObject dataAccessObject = new DataAccessObject();

//    /**
//     * Get the GetGradeUseCase object.
//     * @return GetGradeUseCase object.
//     */
//    public GetGradeUseCase getGradeUseCase() {
//        return new GetGradeUseCase(dataAccessObject);
//    }

    /**
     * Get the LogGradeUseCase object.
     * @return LogGradeUseCase object.
     */
    public CreateResourceUseCase createResourceUseCase() {
        return new CreateResourceUseCase(dataAccessObject);
    }

//    /**
//     * Get the FormTeamUseCase object.
//     * @return FormTeamUseCase object.
//     */
//    public FormTeamUseCase formTeamUseCase() {
//        return new FormTeamUseCase(dataAccessObject);
//    }
//
//    /**
//     * Get the JoinTeamUseCase object.
//     * @return JoinTeamUseCase object.
//     */
//    public JoinTeamUseCase joinTeamUseCase() {
//        return new JoinTeamUseCase(dataAccessObject);
//    }
//
//    /**
//     * Get the LeaveTeamUseCase object.
//     * @return LeaveTeamUseCase object.
//     */
//    public LeaveTeamUseCase leaveTeamUseCase() {
//        return new LeaveTeamUseCase(dataAccessObject);
//    }
//
//    /**
//     * Get the GetAverageGradeUseCase object.
//     * @return GetAverageGradeUseCase object.
//     */
//    public GetAverageGradeUseCase getAverageGradeUseCase() {
//        return new GetAverageGradeUseCase(dataAccessObject);
//    }
//
//    /**
//     * Get the GetTopGradeUseCase object.
//     * @return GetTopGradeUseCase object.
//     */
//    public GetTopGradeUseCase getTopGradeUseCase() {
//        return new GetTopGradeUseCase(dataAccessObject);
//    }
}
