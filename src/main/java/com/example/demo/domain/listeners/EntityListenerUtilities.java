package com.example.demo.domain.listeners;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.*;
import com.example.demo.repository.ExamResultDao;
import com.example.demo.repository.ExamResultStateDao;
import com.example.demo.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static com.example.demo.config.Constants.EXAM_RESULT_STATE_NOT_CHECKED;
import static com.example.demo.config.Constants.ROLE_USER;

@Component
public class EntityListenerUtilities {

    private static ExamResultDao examResultDao;

    private static ExamResultStateDao examResultStateDao;

    private static RoleDao roleDao;

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public EntityListenerUtilities(ExamResultDao examResultDao, ExamResultStateDao examResultStateDao,
                                   RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.examResultDao = examResultDao;
        this.examResultStateDao = examResultStateDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    static void setUserEncodedPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    static void setUserDefaultRole(User user) {
        if (Objects.isNull(user.getRole())) {
            user.setRole(roleDao.getOne(ROLE_USER));
        }
    }

    static void setExamResultDefaultOwner(ExamResult examResult) {
        if (Objects.isNull(examResult.getEnrollee())) {
            examResult.setEnrollee(AuthenticationUtilities.getCurrentEnrollee());
        }
    }

    static void setExamResultDefaultState(ExamResult examResult) {
        if (Objects.isNull(examResult.getState())) {
            examResult.setState(examResultStateDao.getOne(EXAM_RESULT_STATE_NOT_CHECKED));
        }
    }

    static void setApplicationDefaultOwner(Application application) {
        if (Objects.isNull(application.getEnrollee())) {
            application.setEnrollee(AuthenticationUtilities.getCurrentEnrollee());
        }
    }

    static void setApplicationPoint(Application application) {
        if (Objects.isNull(application.getPoint())) {
            Enrollee enrollee = application.getEnrollee();
            Speciality speciality = application.getSpeciality();
            List<Subject> neededSubjects = speciality.getSubjects();
            Double certificateWeight = speciality.getCertificateWeight();
            List<ExamResult> examResults = examResultDao.getExamResultsByEnrolleeId(enrollee.getId());
            Double averageExamMark = examResults.stream()
                    .filter(examResult -> neededSubjects.contains(examResult.getSubject()))
                    .flatMapToInt(examResult -> IntStream.of(examResult.getMark()))
                    .average().orElse(0);
            Double resultMark = averageExamMark * (1 - certificateWeight) + enrollee.getCertificateAverageMark() * certificateWeight;
            application.setPoint(resultMark);
        }
    }
}
