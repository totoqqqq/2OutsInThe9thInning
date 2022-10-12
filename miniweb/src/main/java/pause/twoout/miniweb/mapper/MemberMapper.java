package pause.twoout.miniweb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import twoout.miniweb.dto.Member;

@Mapper
public interface MemberMapper {
	ArrayList<Member> searchall();
	ArrayList<Member> search();
}
