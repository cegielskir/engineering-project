package com.cgk.engineering.team.simpleclient.algorithm;

import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LongestCommonSubstringTest {
    private LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
    private final static String TEXT_1 = "This is very short text special to comparison";
    private final static String LONG_TEXT_1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec molestie vehicula ultrices. " +
            "Phasellus convallis sapien vel libero ullamcorper consectetur vel ut mi. Morbi auctor tortor laoreet risus rhoncus blandit." +
            " Duis sit amet faucibus tortor. Etiam rhoncus feugiat leo, ac semper leo. Cras faucibus nec ex sed rhoncus. " +
            "Proin maximus facilisis mattis. Etiam at vestibulum felis. Fusce efficitur aliquet ullamcorper. Proin nibh risus, tempus at ornare " +
            "sed, blandit id felis. Phasellus porta, ligula quis ultricies blandit, nisl eros luctus eros, et volutpat lorem diam at nulla. " +
            "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi faucibus nunc at faucibus vehicula. " +
            "Sed quis consequat diam, vitae convallis ipsum. Pellentesque molestie euismod accumsan. " +
            "Integer quis libero et ligula gravida mattis gravida ac lectus.";
    private final static String LONG_TEXT_2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec molestie vehicula ultrices. " +
            "Phasellus convallis sapien vel libero ullamcorper consectetur vel ut mi. Morbi auctor tortor laoreet risus rhoncus blandit." +
            "Morbi lacus tellus, cursus nec euismod at, faucibus scelerisque urna. Etiam sodales id massa sit amet mollis. " +
            "Etiam ullamcorper at arcu quis lacinia. In consequat convallis diam. Nullam nisl mi, finibus eget erat ut, l" +
            "obortis efficitur erat. Curabitur vel dui sodales, blandit tellus porttito" +
            "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi faucibus nunc at faucibus vehicula. " +
            "Sed quis consequat diam, vitae convallis ipsum. Pellentesque molestie euismod accumsan. " +
            "Integer quis libero et ligula gravida mattis gravida ac lectus.";


    @Test
    public void testTwoEmptyTexts() {
        // given and when
        int result = longestCommonSubstring.lcs("".toCharArray(), "".toCharArray(), 0, 0);

        double percentage = (double)result*100;

        // then
        assertThat(percentage).isEqualTo(0);
    }

    @Test
    public void testTwoSimilarTexts() {
        // given and when
        int result = longestCommonSubstring
                .lcs(LONG_TEXT_1.toCharArray(), LONG_TEXT_2.toCharArray(),
                        LONG_TEXT_1.length(), LONG_TEXT_2.length());
        double percentage = ((double)result/LONG_TEXT_1.length())*100;

        // then
        assertThat(percentage).isCloseTo(33.1, Offset.offset(0.03));
    }

    @Test
    public void shouldCheckTwoIdenticalTexts() {
        // given and when
        int result = longestCommonSubstring
                .lcs(TEXT_1.toCharArray(), TEXT_1.toCharArray(),
                        TEXT_1.length(), TEXT_1.length());

        double percentage = ((double)result/TEXT_1.length())*100;

        // then
        assertThat(percentage).isEqualTo(100.0);
    }
}
