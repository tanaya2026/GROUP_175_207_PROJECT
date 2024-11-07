//package usecase;
//
//import api.GradeDataBase;
//import entity.User;
//
///**
// * The class for the get grade usecase.
// */
//public final class GetGradeUseCase {
//    private final GradeDataBase gradeDatabase;
//
//    public GetGradeUseCase(GradeDataBase gradeDatabase) {
//        this.gradeDatabase = gradeDatabase;
//    }
//
//    /**
//     * Run the get grade action.
//     * @param username The username.
//     * @param course The course (i.e., CSC207).
//     * @return a grade object if successful.
//     */
//    public User getGrade(String username, String course) {
//        return gradeDatabase.getGrade(username, course);
//    }
//}
