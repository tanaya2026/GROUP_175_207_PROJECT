package app;

import api.SlotifyDataBase;
import usecase.FormTeamUseCase;
import usecase.GetAverageGradeUseCase;
import usecase.GetGradeUseCase;
import usecase.GetTopGradeUseCase;
import usecase.JoinTeamUseCase;
import usecase.LeaveTeamUseCase;
import usecase.CreateResourceUseCase;

/**
 * Config class to provide use cases with the necessary dependencies.
 */

public class Config {
    private final SlotifyDataBase slotifyDataBase = new SlotifyDataBase();

//    /**
//     * Get the GetGradeUseCase object.
//     * @return GetGradeUseCase object.
//     */
//    public GetGradeUseCase getGradeUseCase() {
//        return new GetGradeUseCase(slotifyDataBase);
//    }

    /**
     * Get the LogGradeUseCase object.
     * @return LogGradeUseCase object.
     */
    public CreateResourceUseCase createResourceUseCase() {
        return new CreateResourceUseCase(slotifyDataBase);
    }

//    /**
//     * Get the FormTeamUseCase object.
//     * @return FormTeamUseCase object.
//     */
//    public FormTeamUseCase formTeamUseCase() {
//        return new FormTeamUseCase(slotifyDataBase);
//    }
//
//    /**
//     * Get the JoinTeamUseCase object.
//     * @return JoinTeamUseCase object.
//     */
//    public JoinTeamUseCase joinTeamUseCase() {
//        return new JoinTeamUseCase(slotifyDataBase);
//    }
//
//    /**
//     * Get the LeaveTeamUseCase object.
//     * @return LeaveTeamUseCase object.
//     */
//    public LeaveTeamUseCase leaveTeamUseCase() {
//        return new LeaveTeamUseCase(slotifyDataBase);
//    }
//
//    /**
//     * Get the GetAverageGradeUseCase object.
//     * @return GetAverageGradeUseCase object.
//     */
//    public GetAverageGradeUseCase getAverageGradeUseCase() {
//        return new GetAverageGradeUseCase(slotifyDataBase);
//    }
//
//    /**
//     * Get the GetTopGradeUseCase object.
//     * @return GetTopGradeUseCase object.
//     */
//    public GetTopGradeUseCase getTopGradeUseCase() {
//        return new GetTopGradeUseCase(slotifyDataBase);
//    }
}
