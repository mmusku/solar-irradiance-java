package com.solarIrradiance;

public enum EnumMonthes {
	jan (1),
	feb (2),
	mar (3),
	apr (4),
	may (5),
	jun (6),
	jul (7),
	aug (8),
	sep (9),
	oct (10),
	nov (11),
	dec (12);

	private final int monthCode;

	EnumMonthes(int p_mnthCode) {
        this.monthCode = p_mnthCode;
    }
    
    public int getMonthCode() {
        return this.monthCode;
    }
}
