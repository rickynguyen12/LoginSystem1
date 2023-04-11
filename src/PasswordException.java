public class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
    public static class UpperCaseCharacterMissing extends PasswordException {
        public UpperCaseCharacterMissing(String message) {
            super(message);
        }
    }

    public static class LowerCaseCharacterMissing extends PasswordException {
        public LowerCaseCharacterMissing(String message) {
            super(message);
        }
    }

    public static class SpecialCharacterMissing extends PasswordException {
        public SpecialCharacterMissing(String message) {
            super(message);
        }
    }

    public static class NumberCharacterMissing extends PasswordException {
        public NumberCharacterMissing(String message) {
            super(message);
        }
    }

    public static class Minimum8CharactersRequired extends PasswordException {
        public Minimum8CharactersRequired(String message) {
            super(message);
        }
    }
}