package com.github.lowton.jwa.action.effect.dto;

public record Duration(int turns, int attacks) {
	
	public boolean isInstant() {
		return this.turns == 0 && this.attacks == 0;
	}
	
	public static Duration instant() {
		return new Duration(0, 0);
	}
}
