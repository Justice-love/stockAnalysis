package org.eddy.util;

import org.eddy.swing.entity.Swing;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/21.
 */
public class SwingUtil {

    public static List<Swing> assemble(List<Swing> source) {
        Map<String, List<Swing>> swingMap = source.stream().collect(Collectors.groupingBy(s -> s.getId()));
        return source.stream().filter(swing -> swing.isRoot()).map(swing -> {
            assemble(swing, swingMap);
            return swing;
        }).collect(Collectors.toList());
    }

    private static void assemble(Swing swing, Map<String, List<Swing>> swingMap) {
        if (swing.hasChild()) {
            Swing child = swingMap.get(swing.getChild().getId()).get(0);
            assemble(child, swingMap);
            swing.setChild(child);
        }
    }

}
