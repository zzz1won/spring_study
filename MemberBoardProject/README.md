## 프로젝트 정의 
MemberBoardProject
회원제 게시판 프로젝트 진행

### 211208-211214
### 패키지
00. Main
    - Controller
01. Member
    - Controller
    - Service
    - Repository
    - Mapper
02. Board
    - Controller
    - Service
    - Repository
    - Mapper
03. Comment
    - Controller
    - Service
    - Repository
    - Mapper

### DTO
01. MemberDTO
    @Data
    public class MemberDTO {

	
	private long m_number;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_email;
	private String m_phone;
	
	private MultipartFile m_file;
	// jsp에서 컨트롤러로 넘어올 때 파일 자체를 담는 필드
	// db에는 파일을 담는게 아니라 파일 이름을 담는다. 파일 자체는 별도의 경로에 저장한다.
	private String m_filename;
}
02. BoardDTO
    @Data	//2021-12-07 dto
    public class BoardDTO {

	private long b_number;
	private String b_title;
	private String m_id;
	private String b_writer;
	private String b_contents;
	private int b_hits;
	private Timestamp b_date;
	
	private MultipartFile b_file;
	private String b_filename;	
}
03. CommentDTO
    @Data
    public class CommentDTO {

	private long c_number;
	private long b_number;
	private String c_writer;
	private String c_comment;
	private Timestamp c_date;
}
04. PageDTO
    @Data
    public class PageDTO {

	private int page;
	private int maxPage;
	private int minPage;
	private int startPage;
	private int endPage;
}


## 테이블정의 
    use spring_study;

    -- 회원데이터 관리 테이블
    create table member_table	(
    m_number bigint auto_increment,
    m_id varchar(15) unique not null,
    m_pw varchar(20) not null,
    m_name varchar(10) not null,
    m_email varchar(20),
    m_phone varchar(13),
    m_filename varchar(300),

    constraint primary key (m_number)
    );

    -- 게시글 관리 테이블
    create table board_table (
    b_number bigint auto_increment,
    m_id varchar(15),
    b_title varchar(50),
    b_writer varchar(10),
    b_contents varchar(200),
    b_hits int default 0,
    b_date timestamp,
    b_filename varchar(100),

    constraint primary key (b_number),
    foreign key(m_id) references member_table(m_id)
    );


    -- 댓글 관리 테이블
    create table comment_table (
    c_number bigint auto_increment,
    b_number bigint,
    c_writer varchar(10),
    c_comment varchar(150),
    c_date timestamp,

    constraint primary key (c_number),
    constraint fk_comment_table foreign key(b_number) references board_table(b_number)
    );


## 정리할 때
01. Member
    1. 회원가입 기능
        save.jsp > MemberController.save(MemberDTO member) > MemberService.save(MemberDTO member) -> MemberRepository.save -> member-mapper.save <-> DB
    2. 로그인 기능
        login.jsp > MemberController.login(MemberDTO member) > MemberService.login(MemberDTO member) > MemberRepository.login -> member-mapper.login <-> DB
    3. 로그아웃 기능
        MemberController.logout(HttpSession session) > MemberService.logout
    4. (관리자) 전체 회원 출력 기능
        memberAll.jsp > MemberController.memberAll() > MemberService.memberAll() > MemberRepository.memberAll() > member-mapper.memberAll <-> DB
    5. 마이페이지 출력 기능
        mypage.jsp > MemberController.mypage(long m_number)
    6. 마이페이지 수정 기능
        updatepage.jsp > MemberController.memberUpdate(long m_number) > MemberService.update(MemberDTO member) > MemberRepository.update(MemberDTO member) > member-mapper.update <-> DB
    7. (관리자) 회원 삭제 기능
        MemberController.memberDelete(long m_number) > MemberService.delete(long m_number) > MemberRepository.delete(long m_number) > member-mapper.delete <-> DB


02. Board
    1. 글쓰기 기능
        save.jsp > BoardController.save() > BoardService.save(BoardDTO board) -> BoardRepository.save(BoardDTO board) -> board-mapper.save <-> DB
    2. 글목록 전체 출력 기능
        boardAll.jsp > BoardCotroller.boardAll() > BoardService.boardAll() > BoardRepository.boardAll() > board-mapper.boardAll <-> DB
    3. 페이징처리 기능(많으니까 이따가❤)
        - BoardCotroller.paging(int page) > BoardService.paging > BoardRepository.boardCout() > board-mapper.count <-> DB
        -                                 > BoardService.pagingList > BoardRepository.pagingList(int pagingStart) > board-mapper.pagingList <-> DB
        -                                                           > BoardRepository.pagingList1(Map<String, Integer> pagingParam) > board-mapper.pagingList1 <-> DB
    4. 글 조회 기능
        boardDetail.jsp > BoardCotroller.detail(int page, long b_number) > BoardService.detailNo(long b_number) > BoardRepository.detailNo(long b_number) > board-mapper.detailNo <-> DB
             + 조회수 증가 BoardService.hits(long b_number) > BoardRepository.hits(long b_number) > board-mapper.hits <-> DB 
    5. 글 삭제 기능(작성자, 관리자)
        BoardCotroller.delete(int b_number) > BoardService.delete(long b_number) > BoardRepository.delete(long b_number) > board-mapper.delete <-> DB
    6. 글 수정 출력 기능
        BoardCotroller.updateForm(long b_number)
    6. 글 수정 처리 기능
        boardUpdate.jsp > BoardCotroller.update(BoardDTO board) > BoardService.bupdate(BoardDTO board) > BoardRepository.update(Board board) > board-mapper.bupdate <-> DB
    6. 검색 기능
        BoardCotroller.search(String searchtype, String keyword) > BoardService.search(String searchtype, String keyword) > BoardRepository.search(Map<String, String> searchParam) > board-mapper.search <-> DB

03. Comment
    1. 코멘트 기능
        boardDetail.jsp > CommentController.save(CommentDTO comment) > CommentService.save(CommentDTO comment) > CommentRepository.save(CommentDTO comment) > comment-mapper.commentSave <-> DB
    2. 코멘트 출력기능
        BoardController.detail - commentList > CommentController.save(CommentDTO comment) > CommentService.commentAll(long b_number) > CommentRepository.commentAll(long b_number) > comment-mapper.commentAll <-> DB