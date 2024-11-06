package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Availability;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        final Availability team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members

        for (String user : team.getMembers()) {
            Grade userGrade = gradeDataBase.getGrade(user, course);
            sum += userGrade.getGrade();
        }

        count = team.getMembers().length;

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
