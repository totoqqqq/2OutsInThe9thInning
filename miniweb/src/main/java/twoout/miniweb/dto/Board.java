package twoout.miniweb.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {
	@NonNull
	private String memberID;
	private String boardID;
	@NonNull
	private String boardTitle;
	@NonNull
	private String boardContent;
	private Timestamp createDate;
	private int viewcount;
}
