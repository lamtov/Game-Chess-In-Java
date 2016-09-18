package core;

public class ArtificialIntelligence implements Runnable {
	private PositionBoard positionBoard;
	private GameSetting gameSetting;

	public ArtificialIntelligence(GameSetting gameSetting, PositionBoard positionBoard) {
		this.positionBoard = positionBoard;
		this.gameSetting = gameSetting;
	}

	public PositionBoard getNextPosition() {
		this.run();
		return positionBoard.getBestChild();
	}

	public void free() {
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*********************************************************************************************************
	 * -------------------CODE USING TO FIND ALphaBeta OF
	 * POSITIONBOARD----------------------------------- --------------RETURN
	 * VALUE OF BESTCHILD AND SET BESTCHILD POSITION IS THE POSITION BOARD AFTER
	 * AI CHOISE-----
	 * -------------------------------------------------------------------------
	 *******************************************************************************************************/
	private int AlphaBeta(int alpha, int beta, PositionBoard positionBoard1) {
		positionBoard1.setValue(); // value = AiValue - HumanValue
		if (positionBoard1.getDepth() != 0) {
			if (positionBoard1.getValue() < 15000 && positionBoard1.getValue() > -15000) {
				positionBoard1.setListChildPosition();
			}
		}
		int alphaBeta = 0;
		if (positionBoard1.getDepth() == 0 || positionBoard1.getListChildPosition().size() == 0) {
			alphaBeta = positionBoard1.getValue();
			return alphaBeta;
		}

		if ((this.gameSetting.getLevel() - positionBoard1.getDepth()) % 2 == 0) {
			int bestChild = -10000000;

			int i = 0;
			while (i < positionBoard1.getListChildPosition().size() && bestChild < beta) {
				if (bestChild > alpha) {
					alpha = bestChild;
				}
				int value = AlphaBeta(alpha, beta, positionBoard1.getListChildPosition().get(i));
				if (value > bestChild) {
					bestChild = value;
					positionBoard1.setBestChild(positionBoard1.getListChildPosition().get(i));

				}
				i++;
			}
			alphaBeta = bestChild;

		}
		if ((this.gameSetting.getLevel() - positionBoard1.getDepth()) % 2 == 1) {
			int bestChild = 10000000;
			int i = 0;
			while (i < positionBoard1.getListChildPosition().size() && bestChild > alpha) {
				if (bestChild < beta) {
					beta = bestChild;
				}
				int value = AlphaBeta(alpha, beta, positionBoard1.getListChildPosition().get(i));
				if (value < bestChild) {
					bestChild = value;
					positionBoard1.setBestChild(positionBoard1.getListChildPosition().get(i));
				}
				i++;
			}
			alphaBeta = bestChild;
		}
		positionBoard1.free();
		return alphaBeta;

	}

	/*************************************************************************************************************
	 * /////////////////////////////////////////////////////////////////////////
	 * //////////////////////////////////////
	 *************************************************************************************************************/
	@Override
	public void run() {
		if (GameSetting.rootLevel == 5) {
			gameSetting.setLevel(4);
			PositionBoard positionBoard2 = positionBoard.copy(positionBoard.getDepth(), gameSetting);
			positionBoard2.setListChildPosition();
			if (positionBoard2.getListChildPosition().size() <= 20) {
				gameSetting.setLevel(5);
			}
			this.positionBoard.setDepth(gameSetting.getLevel());
		}
		if (GameSetting.rootLevel == 6) {
			gameSetting.setLevel(4);
			PositionBoard positionBoard2 = positionBoard.copy(positionBoard.getDepth(), gameSetting);
			positionBoard2.setListChildPosition();
			if (positionBoard2.getListChildPosition().size() <= 20) {
				gameSetting.setLevel(5);
			}
			boolean isHauLife = false;
			for (int i = 0; i < this.positionBoard.getListPiecesAi().size(); i++) {
				if (this.positionBoard.getListPiecesAi().get(i).getName().equals("Hau")) {
					isHauLife = true;
				}
			}
			for (int i = 0; i < this.positionBoard.getListPiecesHuman().size(); i++) {
				if (this.positionBoard.getListPiecesHuman().get(i).getName().equals("Hau")) {
					isHauLife = true;
				}
			}
			if (isHauLife == false) {
				PositionBoard positionBoard3 = positionBoard.copy(positionBoard.getDepth(), gameSetting);
				positionBoard3.setListChildPosition();
				if (positionBoard3.getListChildPosition().size() <= 12) {
					gameSetting.setLevel(6);
				}
				
			}
			this.positionBoard.setDepth(gameSetting.getLevel());
		}
		System.out.println("lv : " +gameSetting.getLevel());
		System.out.println("depth " + positionBoard.getDepth());
		AlphaBeta(-10000000, 10000000, this.positionBoard);
	}

	/***********************************************************************************************************
	 * /////////////////// CODE ONLY USING IN WATCH MODE
	 * 
	 * @return POSITION BOARD AI DEVICE HUMAN TO CHOISE
	 * 
	 ************************************************************************************************/
	public PositionBoard getNextHumanPosition() {
		AlphaBetaForHuman(-10000000, 10000000, this.positionBoard);
		if (gameSetting.isTesting()) {
			gameSetting.setTesting(false);
			gameSetting.setWatchMode(false);
		}
		return positionBoard.getBestChild();
	}

	private int AlphaBetaForHuman(int alpha, int beta, PositionBoard positionBoard1) {
		positionBoard1.setValue();

		if (positionBoard1.getDepth() != 0) {
			if (positionBoard1.getValue() < 15000 && positionBoard1.getValue() > -15000) {
				positionBoard1.setListChildPositionForHumanInWatchMode();
			}
		}
		print("[" + alpha + ";" + beta + "]");
		int alphaBeta = 0;
		if (positionBoard1.getDepth() == 0 || positionBoard1.getListChildPosition().size() == 0) {
			alphaBeta = positionBoard1.getValue();
			print("depth = " + positionBoard1.getDepth() + "Day la move = ");
			if (positionBoard1.getDepth() != gameSetting.getLevel()) {
				positionBoard1.getParentMove().show();
			}
			print("value = " + positionBoard1.getValue());
			return alphaBeta;
		}
		print("depth = " + positionBoard1.getDepth() + "Day la move = ");
		if (positionBoard1.getDepth() % 2 == gameSetting.getLevel() % 2) {
			print("May se chon nut MIn");
		} else {
			print("May2 se chon nut Max");
		}
		if (positionBoard1.getDepth() != gameSetting.getLevel()) {
			positionBoard1.getParentMove().show();
		}
		print("So luong nut con = " + positionBoard1.getListChildPosition().size());
		print("Cac con la :");
		for (int i = 0; i < positionBoard1.getListChildPosition().size(); i++) {
			positionBoard1.getListChildPosition().get(i).getParentMove().show();
		}
		if ((this.gameSetting.getLevel() - positionBoard1.getDepth()) % 2 != 0) {
			int bestChild = -10000000;

			int i = 0;
			while (i < positionBoard1.getListChildPosition().size() && bestChild < beta) {
				if (bestChild > alpha) {
					alpha = bestChild;
				}

				int value = AlphaBetaForHuman(alpha, beta, positionBoard1.getListChildPosition().get(i));

				if (value > bestChild) {
					bestChild = value;
					positionBoard1.setBestChild(positionBoard1.getListChildPosition().get(i));
					print("Day la cai bestchild " + "cua depth =" + positionBoard1.getDepth() + "move"
							+ positionBoard1.getParentMove().toString() + "hien tai :"
							+ positionBoard1.getListChildPosition().get(i).getValue());
					positionBoard1.getListChildPosition().get(i).getParentMove().show();

				}
				i++;
			}

			alphaBeta = bestChild;

		}
		if ((this.gameSetting.getLevel() - positionBoard1.getDepth()) % 2 == 0) {
			int bestChild = 10000000;
			int i = 0;
			while (i < positionBoard1.getListChildPosition().size() && bestChild > alpha) {

				if (bestChild < beta) {
					beta = bestChild;
				}
				int value = AlphaBetaForHuman(alpha, beta, positionBoard1.getListChildPosition().get(i));
				if (value < bestChild) {
					bestChild = value;
					positionBoard1.setBestChild(positionBoard1.getListChildPosition().get(i));
					print("======================================");
					if (positionBoard1.getParentMove() != null) {
						print("Day la cai bestchild " + "cua depth =" + positionBoard1.getDepth() + "move"
								+ positionBoard1.getParentMove().toString() + "hien tai :"
								+ positionBoard1.getListChildPosition().get(i).getValue());
						print("======================================");
						positionBoard1.getListChildPosition().get(i).getParentMove().show();
					}
				}
				i++;
			}
			alphaBeta = bestChild;
		}
		print("alphabeta " + alphaBeta + "depth " + positionBoard1.getDepth());
		print("==================================================================================");
		positionBoard1.free();
		return alphaBeta;

	}

	public void print(String string) {
		if (gameSetting.isTesting()) {
			System.out.println(string);
		}
	}
	/*************************************************************************************************************
	 * /////////////////////////////////////////////////////////////////////////
	 * //////////////////////////////////////
	 *************************************************************************************************************/

}
