package issou.logic.utils;

public class Enums {
    public enum TargetType {
        AllChars,
        MyChar,
        EnemyChar,
        AllMinions,
        MyMinions,
        EnemyMinions,
        NoTarget;
    }

    public enum MinionType {
        SansType,
        PeupleElu,
        IslamoGauchiste;
    }

    public enum GameState {
        WaitingToStart1,
        WaitingToStart2,
        TurnP1,
        TurnP2,
        Finished
    }
}
