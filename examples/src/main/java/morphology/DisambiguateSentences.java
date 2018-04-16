package morphology;

import java.io.IOException;
import java.util.List;
import zemberek.morphology.analyzer.SentenceAnalysis;
import zemberek.morphology.analyzer.SentenceWordAnalysis;
import zemberek.morphology.analyzer.SingleAnalysis;
import zemberek.morphology.analyzer.TurkishMorphology;
import zemberek.morphology.analyzer.WordAnalysis;

public class DisambiguateSentences {

  TurkishMorphology morphology;

  public DisambiguateSentences(TurkishMorphology morphology) {
    this.morphology = morphology;
  }

  public static void main(String[] args) throws IOException {
    TurkishMorphology morphology = TurkishMorphology.createWithDefaults();

    new DisambiguateSentences(morphology)
        .analyzeAndDisambiguate("Bu akşam kar yağacak gibi.");
  }

  void analyzeAndDisambiguate(String sentence) {
    System.out.println("Sentence  = " + sentence);
    List<WordAnalysis> analyses = morphology.analyzeSentence(sentence);

    System.out.println("Sentence word analysis result:");
    writeParseResult(analyses);

    SentenceAnalysis result = morphology.disambiguate(sentence, analyses);
    System.out.println("\nBest analyses:");
    for (SentenceWordAnalysis sentenceWordAnalysis : result) {
      System.out.println(sentenceWordAnalysis.getAnalysis().formatLong());
    }
  }

  private void writeParseResult(List<WordAnalysis> sentenceAnalysis) {
    for (WordAnalysis entry : sentenceAnalysis) {
      System.out.println("Word = " + entry.getInput());
      for (SingleAnalysis analysis : entry) {
        System.out.println(analysis.formatLong());
      }
    }
  }
}
