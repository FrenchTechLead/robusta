import static java.lang.Math.*;import static org.javascool.macros.Stdin.*;import static org.javascool.macros.Stdout.*;import static org.javascool.compiler.Utils.*;public class C{public static void main(String[] args) {try{ main(); } catch(Exception e) { handleRuntimeExceptions(e); }}
static void main() {
    for (int i = 1; i < 10; i++) {
        println(i);
        sleep(1000);
    }

}
}