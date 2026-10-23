package ar.edu.davinci.a262_am_lessons_bv;

import java.util.ArrayList;

public class ApiResponse {
    private ApiInfo info;
    private ArrayList<Character> results;

    public ApiInfo getInfo() {
        return info;
    }

    public void setInfo(ApiInfo info) {
        this.info = info;
    }

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }
}
