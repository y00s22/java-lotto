package lotto.view;

import lotto.model.LottoNumbers;
import utils.IntegerUtils;

import java.util.Scanner;

public class inputViewManual {
    public int inputManualLottoNumberView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();

    }

    public LottoNumbers inputLottoNumberView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return IntegerUtils.splitAndParseLottoNumber(scanner.nextLine());
    }
}
