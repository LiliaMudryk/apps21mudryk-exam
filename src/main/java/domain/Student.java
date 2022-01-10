package domain;

import json.*;

import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private Tuple<String, Integer>[] examsResults;
    private final static int passGrade = 3;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        // ToDo
        super(name, surname, year);
        this.examsResults = exams;
    }

    private class Exam {
        String subject;
        int grade;
        boolean status;
        public Exam(Tuple<String, Integer> result){
            this.subject = result.key;
            this.grade = result.value;
            this.status = passed(grade);

        }
        public boolean passed(int grade) {
            if (grade >= passGrade) {
                return true;
            }
            return false;
        }
        public JsonObject createJsonObject(){
            JsonPair subjectPair = new JsonPair("course", new JsonString(subject));
            JsonPair gradePair = new JsonPair("mark", new JsonNumber(grade));
            JsonPair statusPair = new JsonPair("passed", new JsonBoolean(status));
            return new JsonObject(subjectPair,gradePair,statusPair);
        }

    }

    @Override
    public JsonObject toJsonObject() {
        // ToDo
        JsonObject jsonObject = super.toJsonObject();
        JsonObject[] exams = new JsonObject[examsResults.length];
        int index = 0;
        for (Tuple<String, Integer> examData: examsResults) {
            Exam exam = new Exam(examData);
            JsonObject jsonObjectExam = exam.createJsonObject();
            exams[index] = jsonObjectExam;
            index += 1;
        }
        jsonObject.add(new JsonPair("exams", new JsonArray(exams)));
        return jsonObject;

    }
}