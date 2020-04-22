package de.srs.bulletinboard.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pair {

	private Object key;
	private Object value;
	
	public Pair(Object key, Object value){
		this.key = key;
		this.value = value;
	}
}
