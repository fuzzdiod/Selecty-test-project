//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
    /*
    Из нашего модуля в рамках метода String internalSearch(Request searchRequest)
делается 3 параллельных вызовов внешнего сервиса

List<String> externalServisSearch(String bdIndex, Request searchRequest)
который может вернуть список значений или ошибку, включая NOT_FOUND.
bdIndex - берется из заранее известного, не изменяемого списка

По результатам всех трех вызовов нам надо отдать консолидированный ответ изходя из требований:
1. если все ответы пришли пустыми или закончились с ошибкой NOT_FOUND - интрепритируется как NOT_FOUND.
2. если есть непустой ответ хотя бы от одного запроса - отдаем первый элемент из списка от любого ответа
3. если нет непустых ответов и среди ошибок есть ошибки отличные от NOT_FOUND -
завершаем вызов с ошибкой FAILED_PRECONDITION.

     */
}