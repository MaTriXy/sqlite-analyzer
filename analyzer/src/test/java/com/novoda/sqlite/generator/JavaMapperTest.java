package com.novoda.sqlite.generator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JavaMapperTest {

    @Test
    public void should_map_underscores_to_camel_case() {
        assertJava("hello_column", "HelloColumn", "helloColumn");
    }

    @Test
    public void should_remove_leading_underscore() {
        assertJava("_column", "Column", "column");
    }

    @Test
    public void should_map_umlauts() {
        assertJava("hälööö", "Hälööö", "hälööö");
    }

    @Test
    public void should_map_sqlite_name_disambiguation() {
        assertJava("id:1", "Id1", "id1");
    }

    @Test
    public void should_map_dash_to_camel_ase() {
        assertJava("PRIME-CO", "PrimeCo", "primeCo");
    }

    @Test
    public void should_map_single_character() {
        assertJava("Ä", "Ä", "ä");
    }

    @Test
    public void should_camelize_all_uppercase_underscored_column() {
        assertJava("COLUMN_ID", "ColumnId", "columnId");
    }

    @Test
    public void should_camelize_lower_underscored_column() {
        assertJava("column_id", "ColumnId", "columnId");
    }

    @Test
    public void should_camelize_mixedcase_column() {
        assertJava("columnID", "Columnid", "columnid");
    }

    @Test
    public void should_camelize_all_uppercase_column() {
        assertJava("COLUMNID", "Columnid", "columnid");
    }

    private static void assertJava(String source, String accessor, String name) {
        assertEquals(accessor, JavaMapper.javaAccessor(source));
        assertEquals(name, JavaMapper.javaName(source));
    }
}
