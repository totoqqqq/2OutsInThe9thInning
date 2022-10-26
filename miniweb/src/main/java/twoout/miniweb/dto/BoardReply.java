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
public class BoardReply {
	@NonNull
	private String replayContent;
	private Timestamp createdate;
	@NonNull
	private String memberID;
	@NonNull
	private String boardID;
}
