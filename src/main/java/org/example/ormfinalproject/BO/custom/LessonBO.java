package org.example.ormfinalproject.BO.custom;

import org.example.ormfinalproject.BO.SuperBO;
import org.example.ormfinalproject.model.LessonDTO;

import java.util.ArrayList;

public interface LessonBO extends SuperBO {
    ArrayList<LessonDTO> getAllLesson();
}
