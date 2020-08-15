package model;

import java.io.Serializable;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;

/**
 *
 * @author Lê Quang Đạo
 */
public class Answer implements Serializable{
    private Student student;
    private Object[] answer;
    private boolean isRight;
    private boolean alreadyRegistration;
    private static final long serialVersionUID = 2L;

    public Answer(Student student, Object[] answer, boolean isRight, boolean alreadyRegistration) {
        this.student = student;
        this.answer = answer;
        this.isRight = isRight;
        this.alreadyRegistration = alreadyRegistration;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Object[] getAnswer() {
        return answer;
    }

    public void setAnswer(Object[] answer) {
        this.answer = answer;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public boolean isAlreadyRegistration() {
        return alreadyRegistration;
    }

    public void setAlreadyRegistration(boolean alreadyRegistration) {
        this.alreadyRegistration = alreadyRegistration;
    }
    
    
    
}
