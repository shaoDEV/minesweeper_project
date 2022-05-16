package de.shao.game;

public enum Pictures{
    ONE{
        @Override
        public String toString(){
            return "src/resources/ONE.png";
        }
    },
    TWO{
        @Override
        public String toString(){
            return "src/resources/TWO.png";
        }
    },
    THREE{
        @Override
        public String toString(){
            return "src/resources/THREE.png";
        }
    },
    FOUR{
        @Override
        public String toString(){
            return "src/resources/FOUR.png";
        }
    },
    FIVE{
        @Override
        public String toString(){
            return "src/resources/FIVE.png";
        }
    },
    SIX{
        @Override
        public String toString(){
            return "src/resources/SIX.png";
        }
    },
    SEVEN{
        @Override
        public String toString(){
            return "src/resources/SEVEN.png";
        }
    },
    EIGHT{
        @Override
        public String toString(){
            return "src/resources/EIGHT.png";
        }
    },
    NONE{
        @Override
        public String toString(){
            return "src/resources/OPEN_FIELD.png";
        }
    },
    BLOCKED{
        @Override
        public String toString(){
            return "src/resources/FLAG.png";
        }
    },
    CLOSED{
        @Override
        public String toString(){
            return "src/resources/CLOSED_FIELD.png";
        }
    }

}
