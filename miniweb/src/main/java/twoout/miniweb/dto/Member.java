package twoout.miniweb.dto;

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
public class Member {
	@NonNull
	String memberID;
	@NonNull
	String memberPW;
	String nickName;
	String phone;
	String email;
	String zipcode;
	String address;
	String buliding;
	String membership;
}