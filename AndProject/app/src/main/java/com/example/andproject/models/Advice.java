package com.example.andproject.models;

public class Advice {

    private final Slip slip;

    public Advice(Slip slip) {
        this.slip = slip;
    }

    public Slip getSlip() {
        return slip;
    }

    public static class Slip {
        private final int id;
        private final String advice;

        public Slip(int id, String advice) {
            this.id = id;
            this.advice = advice;
        }

        public int getId() {
            return id;
        }

        public String getAdvice() {
            return advice;
        }
    }
}
