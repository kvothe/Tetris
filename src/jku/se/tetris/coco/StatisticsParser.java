package jku.se.tetris.coco;

public class StatisticsParser {

	public static void main(String[] args) {
		System.out.println("___________________________");
		System.out.println("StatisticsParser (Markus Hofmarcher, November 2011)");

		if (args.length > 0) {
			System.out.println("   Reading source file " + args[0]);
			Scanner scanner = new Scanner(args[0]);
			Parser parser = new Parser(scanner);

			System.out.println();
			parser.Parse();

			if (parser.errors.count == 1) {
				System.out.println("-- 1 error dectected");
			} else {
				System.out.println("-- " + parser.errors.count + " errors dectected");
			}

		} else {
			System.out.println("Syntax: StatisticsParser <statistics file>");
		}
		System.out.println("___________________________");
	}

}
