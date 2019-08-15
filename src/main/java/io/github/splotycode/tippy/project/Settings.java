package io.github.splotycode.tippy.project;

import lombok.Data;

@Data
public class Settings {

    private boolean debugTokens;
    private boolean debugTermTree;

    private AngleType angleType = AngleType.DEGREES;

}
