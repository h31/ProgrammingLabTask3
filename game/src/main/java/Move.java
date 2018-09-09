public class Move {
    Listeners listeners;

    public Move(Listeners listeners) {
        this.listeners = listeners;
    }

    void moveToRight() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if (listeners.Numbers2D[i][j] != 0) {
                    listeners.temporary = listeners.Numbers2D[i][j];
                    listeners.previous = j + 1;
                    while (listeners.previous <= 3 && listeners.Numbers2D[i][listeners.previous] == 0) {
                        listeners.Numbers2D[i][listeners.previous] = listeners.temporary;
                        listeners.Numbers2D[i][listeners.previous - 1] = 0;
                        listeners.previous++;
                        listeners.Counter++;
                    }
                }
            }
        }

        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if (j + 1 < 4
                        && (listeners.Numbers2D[i][j] == listeners.Numbers2D[i][j + 1])
                        && (listeners.Numbers2D[i][j] != 0 || listeners.Numbers2D[i][j + 1] != 0)) {
                    listeners.Numbers2D[i][j + 1] = listeners.Numbers2D[i][j] + listeners.Numbers2D[i][j + 1];
                    listeners.Numbers2D[i][j] = 0;
                    listeners.Counter++;
                    listeners.score += listeners.Numbers2D[i][j + 1];
                    if (listeners.Numbers2D[i][j + 1] == 2048) {
                        listeners.isWin = true;
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if (listeners.Numbers2D[i][j] != 0) {
                    listeners.temporary = listeners.Numbers2D[i][j];
                    listeners.previous = j + 1;
                    while (listeners.previous <= 3 && listeners.Numbers2D[i][listeners.previous] == 0) {
                        listeners.Numbers2D[i][listeners.previous] = listeners.temporary;
                        listeners.Numbers2D[i][listeners.previous - 1] = 0;
                        listeners.previous++;
                        listeners.Counter++;
                    }
                }
            }
        }
    }

    void moveUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (listeners.Numbers2D[i][j] != 0) {
                    listeners.temporary = listeners.Numbers2D[i][j];
                    listeners.previous = i - 1;
                    while (listeners.previous >= 0 && listeners.Numbers2D[listeners.previous][j] == 0) {
                        listeners.Numbers2D[listeners.previous][j] = listeners.temporary;
                        listeners.Numbers2D[listeners.previous + 1][j] = 0;
                        listeners.previous--;
                        listeners.Counter++;
                    }
                }
            }
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (i + 1 < 4
                        && (listeners.Numbers2D[i][j] == listeners.Numbers2D[i + 1][j])
                        && (listeners.Numbers2D[i][j] != 0 || listeners.Numbers2D[i + 1][j] != 0)) {
                    listeners.Numbers2D[i][j] = listeners.Numbers2D[i][j] + listeners.Numbers2D[i + 1][j];
                    listeners.Numbers2D[i + 1][j] = 0;
                    listeners.Counter++;
                    listeners.score += listeners.Numbers2D[i][j];
                    if (listeners.Numbers2D[i][j] == 2048) {
                        listeners.isWin = true;
                    }
                }
            }
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (listeners.Numbers2D[i][j] != 0) {
                    listeners.temporary = listeners.Numbers2D[i][j];
                    listeners.previous = i - 1;
                    while (listeners.previous >= 0 && listeners.Numbers2D[listeners.previous][j] == 0) {
                        listeners.Numbers2D[listeners.previous][j] = listeners.temporary;
                        listeners.Numbers2D[listeners.previous + 1][j] = 0;
                        listeners.previous--;
                        listeners.Counter++;
                    }
                }
            }
        }
    }

    void moveDown() {
        for (int j = 3; j >= 0; j--) {
            for (int i = 3; i >= 0; i--) {
                if (listeners.Numbers2D[i][j] != 0) {
                    listeners.temporary = listeners.Numbers2D[i][j];
                    listeners.previous = i + 1;
                    while (listeners.previous <= 3 && listeners.Numbers2D[listeners.previous][j] == 0) {
                        listeners.Numbers2D[listeners.previous][j] = listeners.temporary;
                        listeners.Numbers2D[listeners.previous - 1][j] = 0;
                        listeners.previous++;
                        listeners.Counter++;
                    }
                }
            }
        }
        for (int j = 3; j >= 0; j--) {
            for (int i = 3; i >= 0; i--) {
                if (i + 1 < 4
                        && (listeners.Numbers2D[i][j] == listeners.Numbers2D[i + 1][j])
                        && (listeners.Numbers2D[i][j] != 0 || listeners.Numbers2D[i + 1][j] != 0)) {
                    listeners.Numbers2D[i + 1][j] = listeners.Numbers2D[i][j]
                            + listeners.Numbers2D[i + 1][j];
                    listeners.Numbers2D[i][j] = 0;
                    listeners.Counter++;
                    listeners.score += listeners.Numbers2D[i + 1][j];
                    if (listeners.Numbers2D[i + 1][j] == 2048) {
                        listeners.isWin = true;
                    }
                }
            }
        }

        for (int j = 3; j >= 0; j--) {
            for (int i = 3; i >= 0; i--) {
                if (listeners.Numbers2D[i][j] != 0) {
                    listeners.temporary = listeners.Numbers2D[i][j];
                    listeners.previous = i + 1;
                    while (listeners.previous <= 3 && listeners.Numbers2D[listeners.previous][j] == 0) {
                        listeners.Numbers2D[listeners.previous][j] = listeners.temporary;
                        listeners.Numbers2D[listeners.previous - 1][j] = 0;
                        listeners.previous++;
                        listeners.Counter++;
                    }
                }
            }
        }
    }
}
