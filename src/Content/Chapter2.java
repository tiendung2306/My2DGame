package Content;

import main.GamePanel;
import main.Main;
import time.TimeSystem;

public class Chapter2 extends Chapter{

    long prevTime = 0;
    public boolean isAtClassMrsToan = false, isAtClassMrHoa = false;
    public Chapter2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.ui = gamePanel.ui;
        this.inventory = gamePanel.inventory;
        this.collision = gamePanel.collision;
        this.phone = gamePanel.phone;
        this.missionDescription = gamePanel.missionDescription;
        currentTimeline = 0;
        completedAct = 0;
    }

    void nextTimeline() {
        ++currentTimeline;
        completedAct = 0;
    }

    void Timeline0() {
        if (completedAct == 0)
            Dialogue("Ngày thứ 2 kể từ khi ta chuyển sinh vào thế giới này.");
        if (completedAct == 1)
            Dialogue("Sự yên bình này, cũng khá là tuyệt vời đấy chứ.");
        if (completedAct == 2)
            Dialogue("Ta đã chính thức trở thành thợ săn kể từ hôm nay.");
        if (completedAct == 3)
            Dialogue("Từng là Anh Hùng mà nay lại phải cày cuốc như một tân binh. Trớ trêu thật!");
        if (completedAct == 4){
            Dialogue("Thôi thì hết cách rồi, để xem nhiệm vụ đầu tiên hội đưa ra cho ta là gì.");
            missionDescription.setMissionDescription("Kiểm tra lịch học ở trong app Fhust.");
            gamePanel.myRoom.tilePhone.isMission = true;
            phone.isOpenFhust = false;
        }
        if (completedAct == 5){
            if (phone.isOpenFhust && !phone.isDrawPhone) {
                Dialogue("Nhiệm vụ đầu tiên đã phải đối đầu với Boss Nguyễn Thị Toàn và Lê Quang Hòa sao?");
                missionDescription.setMissionDescription("");
                prevTime = TimeSystem.getCurrentSystemTimeInMilliseconds();
            }
        }
        if (completedAct == 6)
            Dialogue("Theo thông tin tình báo thì Nguyễn Thị Toàn là Boss của khu vực 37");
        if (completedAct == 7)
            Dialogue("Đã có biết bao thợ săn phải nằm lại dưới lưỡi bút của cô.");
        if (completedAct == 8)
            Dialogue("Còn Boss Lê Quang Hòa vẫn luôn là một ẩn số mà đến nay hội vẫn chưa khai phá được.");
        if (completedAct == 9)
            Dialogue("Khó nhằn đây!");
        if (completedAct == 10)
            Dialogue("Nhưng đối với anh hùng như ta, nhiệm vụ này đánh giá là hơi dễ.");
        if (completedAct == 11)
            nextTimeline();
    }

    void Timeline1() {
        if (completedAct == 0){
            missionDescription.setMissionDescription("Di chuyển tới tòa D3-5 tại Section 2, tham gia lớp học cô Nguyễn Thị Toàn tại phòng 301.");
            isAtClassMrsToan = false;
            ++completedAct;
        }
        if (completedAct == 1){
            if (isAtClassMrsToan){
                isAtClassMrsToan = false;
                ++completedAct;
                prevTime = TimeSystem.getCurrentSystemTimeInMilliseconds();
            }
        }
        if (completedAct == 2 && TimeSystem.getCurrentSystemTimeInMilliseconds() - prevTime >= 500){
            ui.setDialogueBackground("Classroom");
            ui.setDialogueCharacter("Mrs Toan");
            Dialogue("Anh tê, dừ là buổi mô rồi anh mì mò mặt đến lớp? Tôi tích cho anh 1 dấu.");
        }
        if (completedAct == 3){
            ui.setDialogueCharacter("Empty");
            Dialogue("Aiss, chưa gì đã dính tuyệt chiêu của Boss, mình phải cẩn thận hơn!");
        }
        if (completedAct == 4){
            ui.setDialogueCharacter("Mrs Toan");
            Dialogue("Anh vừa mới tới mô rồi, anh lên bảng giải câu ni được thì tha cho anh.");
        }
        if (completedAct == 5){
            gamePanel.normalClassroom.addTile(gamePanel.normalClassroom.mrsToan);
            ++completedAct;
        }
        if (completedAct == 6){
            if (collision.interactItem.Name.equals("Mrs Toan"))
                Dialogue("Đạo hàm của arctan là cấy chi?");
        }
        if (completedAct == 7){
            ui.setDialogueCharacter("Empty");
            Dialogue("Mình có nghe nhầm không? Câu hỏi này ở thế giới mình đến cả đứa trẻ 5 tuổi cũng trả lời được mà.");
        }
        if (completedAct == 8){
            Dialogue("Hệ thống, giúp ta trả lời câu hỏi này.");
        }
        if (completedAct == 9){
            ui.setDialogueCharacter("System");
            Dialogue("Đã rõ! Câu trả lời là 1/(1+x).");
        }
        if (completedAct == 10){
            ui.setDialogueCharacter("Main Character");
            Dialogue("Thưa cô, đáp án chính là 1/(1+x).");
        }
        if (completedAct == 11){
            ui.setDialogueCharacter("Mrs Toan");
            Dialogue("Sai");
        }
        if (completedAct == 12){
            ui.setDialogueCharacter("Cat Meme");
            Dialogue("...");
        }
        if (completedAct == 13){
            ui.setDialogueCharacter("System");
            Dialogue("...");
        }
        if (completedAct == 14){
            ui.setDialogueCharacter("Mrs Toan");
            Dialogue("Anh về chộ đi, trớp trớp thật. Nỏ mần được cấy ci nên hồn.");
        }
        if (completedAct == 15){
            ui.setDialogueCharacter("Mrs Toan");
            Dialogue("Học hành cho nên thân buổi sau tui gọi anh tiếp.");
        }
        if (completedAct == 16)
            nextTimeline();
    }
    void Timeline2(){
        if (completedAct == 0){
            ui.setDialogueBackground("Empty");
            ui.setDialogueCharacter("Empty");
            Dialogue("Nguy hiểm thật sự, quả là ma pháp tà đạo!");
            missionDescription.setMissionDescription("Tham gia lớp Đại Số của thầy Lê Quang Hòa.");
            isAtClassMrHoa = true;
        }
        if (completedAct == 1){
            if (isAtClassMrHoa){
                ui.setDialogueBackground("Classroom");
                ui.setDialogueCharacter("Mr Hoa");
                Dialogue("qwertyuiopasdfghjklzxcvbnm");
                isAtClassMrHoa = false;
            }
        }
        if (completedAct == 2){
            Dialogue("448877778 777744468855528666777");
        }
        if (completedAct == 3){
            Dialogue("https://www.youtube.com/watch?v=d Qw4w9WgXcQ");
        }
        if (completedAct == 4){
            ui.setDialogueCharacter("Empty");
            Dialogue("...");
        }
        if (completedAct == 5){
            Dialogue("Thôi được rồi.");
        }
        if (completedAct == 6){
            Dialogue("Hôm nay tới đây là đủ rồi");
        }
        if (completedAct == 7){
            ui.setDialogueBackground("Empty");
            missionDescription.setMissionDescription("Di chuyển về nhà và ngủ đi");
            gamePanel.myRoom.tileBed.isMission = true;
            ++completedAct;
        }
        if (completedAct == 8){
            if (collision.interactItem.Name.equals("My Bed"))
                Dialogue("Hoàn thành Chapter 2");
        }
        if (completedAct == 9){
            nextTimeline();
        }
    }
    void Timeline3(){
        gamePanel.currentChapter = gamePanel.chapter3;
        gamePanel.chapter3.completedAct = 0;
    }
    public void update() {
        if (!Main.topGameState().equals("GamePlay") && !Main.topGameState().equals("Inventory")
                && !Main.topGameState().equals("Dialogue") && !Main.topGameState().equals("Dialogue"))
            return;
        switch (currentTimeline) {
            case 0: {
                Timeline0();
                break;
            }
            case 1: {
                Timeline1();
                break;
            }
            case 2: {
                Timeline2();
                break;
            }
            case 3: {
                Timeline3();
                break;
            }
        }
    }

    void Dialogue(String str) {
        if (Main.topGameState().equals("GamePlay")) {
            ui.currentDialog = str;
            Main.pushGameState("Dialogue");
            ui.i = 0;
            ui.timer.setDelay(30);
            ui.isFinishDialogue = false;
            ui.timer.start();
        }
    }
}
