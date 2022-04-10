package com.mailliwonerom.javapocs.net.domain.state;

public enum AlphaCode {
    AL("al","Alabama"),
    AK("ak", "Alaska"),
    AZ("az", "Arizona"),
    AR("ar", "Arkansas"),
    CA("ca", "California"),
    CO("co", "Colorado"),
    CT("ct", "Connecticut"),
    DE("de", "Delaware"),
    DC("dc", "District of Columbia"),
    FL("fl", "Florida"),
    GA("ga", "Georgia"),
    HI("hi", "Hawaii"),
    ID("id", "Idaho"),
    IL("il", "Illinois"),
    IN("in", "Indiana"),
    IA("ia", "Iowa"),
    KS("ks", "Kansas"),
    KY("ky", "Kentucky"),
    LA("ls", "Louisiana"),
    ME("me", "Maine"),
    MD("md", "Maryland"),
    MA("ma", "Massachusetts"),
    MI("mi", "Michigan"),
    MN("mn", "Minnesota"),
    MS("ms", "Mississippi"),
    MO("mo", "Missouri"),
    MT("mt", "Montana"),
    NE("ne", "Nebraska"),
    NV("nv", "Nevada"),
    NH("nh", "New Hampshire"),
    NJ("nj", "New Jersey"),
    NM("nm", "New Mexico"),
    NY("ny", "New York"),
    NC("nc", "North Carolina"),
    ND("nd", "North Dakota"),
    OH("oh", "Ohio"),
    OK("ok", "Oklahoma"),
    OR("or", "Oregon"),
    PA("pa", "Pennsylvania"),
    RI("ri", "Rhode Island"),
    SC("sc", "South Carolina"),
    SD("sd", "South Dakota"),
    TN("tn", "Tennessee"),
    TX("tx", "Texas"),
    UT("ut", "Utah"),
    VT("vt", "Vermont"),
    VA("va", "Virginia"),
    WA("wa", "Washington"),
    WV("wv", "West Virginia"),
    WI("wi", "Wisconsin"),
    WY("wy", "Wyoming");

    private String key;
    private String value;

    AlphaCode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
