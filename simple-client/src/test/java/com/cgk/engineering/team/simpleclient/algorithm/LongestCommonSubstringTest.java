package com.cgk.engineering.team.simpleclient.algorithm;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class LongestCommonSubstringTest {
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

    @Mock
    LongestCommonSubstring longestCommonSubstringMock = Mockito.mock(LongestCommonSubstring.class);

    @Test
    public void shouldCheckTwoIdenticalTexts() {
        // given and when
        int percentage = longestCommonSubstringMock
                .lcs(TEXT_1.toCharArray(), TEXT_1.toCharArray(),
                        TEXT_1.length(), TEXT_1.length());

        // then
        assertEquals(0, percentage);
    }

    @Test
    public void testTwoSimilarTexts() {
        // given and when
        int percentage = longestCommonSubstringMock
                            .lcs(LONG_TEXT_1.toCharArray(), LONG_TEXT_2.toCharArray(),
                                    LONG_TEXT_1.length(), LONG_TEXT_2.length());

        // then
        assertEquals(0, percentage);
    }
}
