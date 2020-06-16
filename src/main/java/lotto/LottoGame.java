package lotto;

import lotto.model.LottoStatistics;
import lotto.model.LottoTicket;
import lotto.model.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;
import utils.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * 뷰에게 구입 금액을 요청한다
 * 구입 금액만큼 로또를 발급한다
 * 뷰에게 당첨 번호를 요청한다
 * 당첨 번호를 로또모델에게 전달하고 당첨된 번호 갯수를 받는다
 * 당첨된 로또 번호를 받아서 통계 모델에게 전달하여 수익률을 받는다
 * 뷰에게 수익률 출력을 요청한다
 */

public class LottoGame {
	private static int prize = 0;

	private static InputView inputView = new InputView();
	private static OutputView outputView = new OutputView();
	private LottoStatistics lottoStatistics = new LottoStatistics();

	public void makeWish() {
		int budget = setBudget();
		int numberOfTickets = setTicketNumber(budget);
		List<LottoTicket> tickets = buyTickets(numberOfTickets);
		int[] winningNumbers = setWinningNumbers();

		for (int i = 0; i < numberOfTickets; i++) {
			Rank rank = tickets.get(i).announceRank(winningNumbers);
			lottoStatistics.gatherResult(rank);
			prize += lottoStatistics.getPrizeMoney(rank);
		}

		outputView.winningResultView();

		double profit = lottoStatistics.calcProfit(prize, budget);
		outputView.printEarningRateView(profit);
	}

	public List<LottoTicket> buyTickets(int numberOfTickets) {
		List<LottoTicket> tickets = new ArrayList<>();

		for (int i = 0; i < numberOfTickets; i++) {
			tickets.add(new LottoTicket());
			outputView.printLottoNumbersView(tickets.get(i).getMyNumbers());
		}

		return tickets;
	}

	public int setTicketNumber(int budget) {
		int numberOfTickets = lottoStatistics.buyLottos(budget);
		outputView.howManyLottoTicketsView(numberOfTickets);
		return numberOfTickets;
	}

	private int setBudget() {
		return inputView.inputBudgetView();
	}

	private int[] setWinningNumbers() {
		String str = inputView.inputWinningNumbersView();
		return IntegerUtils.splitAndParseInt(str);
	}
}
