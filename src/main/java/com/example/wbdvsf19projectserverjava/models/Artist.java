package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="artists")
public class Artist {
	@Id
	private int id;
	private String name;

	public void set(Artist newArtist) {
        this.id = newArtist.id;
        this.name = newArtist.name;
	}

}
