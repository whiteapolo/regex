import StateMachine.StateMachine;

public class Main {
    public static void main(String[] args) {
//        Lexer lexer = new Lexer("[a-z]hello*\\(s|bye)");
//        lexer.printTokens();
        StateMachine sm = new StateMachine("hello");
        sm.oneOrMore();
//        sm.union(new StateMachine("bye"));
//        sm.zeroOrMore();
//        sm.oneOrMore();
//        sm.optional();
        System.out.println(Regex.match(sm, "hello5"));
    }
}