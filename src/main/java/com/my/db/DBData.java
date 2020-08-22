package com.my.db;

import com.my.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBData {

    public static List<Modular> modulars = createModulars();
    public static List<Store> stores = createStores();
    public static List<Section> sections = createSections();
    public static List<Flex> flexes = createFlexes();
    public static List<ModularSection> modularSections = createModularSections();
    public static List<ModularSectionAssignment> modularSectionAssignments = createModularSectionAssignments();
    public static List<ModularSectionItem> modularSectionItems = createModularSectionItems();

    public static List<Modular> createModulars() {
        List<Modular> modulars = new ArrayList<>();

        modulars.add(new Modular(1, 11, new Store("12345"), "Mod1"));
        modulars.add(new Modular(2, 12, new Store("56789"), "Mod2"));
        modulars.add(new Modular(3, 13, new Store("13568"), "Mod3"));
        modulars.add(new Modular(4, 14, new Store("13568"), "Mod4"));
        modulars.add(new Modular(5, 12, new Store("13568"),  "Mod5"));
        return modulars;
    }

    private static List<ModularSection> createModularSections() {
        List<ModularSection> modularSections = new ArrayList<>();
        modularSections.add(new ModularSection(1, 1, new Modular(1)));
        modularSections.add(new ModularSection(2, 2, new Modular(1)));
        modularSections.add(new ModularSection(3, 1, new Modular(2)));
        modularSections.add(new ModularSection(4, 1, new Modular(3)));
        modularSections.add(new ModularSection(5, 2, new Modular(3)));
        modularSections.add(new ModularSection(6, 1, new Modular(4)));
        modularSections.add(new ModularSection(7, 1, new Modular(5)));
        modularSections.add(new ModularSection(8, 2, new Modular(5)));
        return modularSections;
    }

    private static List<ModularSectionAssignment> createModularSectionAssignments() {
        List<ModularSectionAssignment> modularSectionAssignments = new ArrayList<>();

        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(1), new Section("111111"), "John Smith", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(2), new Section("111112"), "Jane Smith", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(3), new Section("222222"), "System", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(4), new Section("333333"), "John Smith", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(5), new Section("333334"), "Jane Smith", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(6), new Section("333335"), "System", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(7), new Section("333336"), "John Smith", LocalDateTime.now()));
        modularSectionAssignments.add(new ModularSectionAssignment(new ModularSection(7), new Section("333339"), "John Doe", LocalDateTime.now()));

        return modularSectionAssignments;
    }

    private static List<ModularSectionItem> createModularSectionItems() {
        List<ModularSectionItem> modularSectionItems = new ArrayList<>();
        modularSectionItems.add(new ModularSectionItem(1, "7111", new ModularSection(1)));
        modularSectionItems.add(new ModularSectionItem(2, "7141", new ModularSection(1)));
        modularSectionItems.add(new ModularSectionItem(1, "7112", new ModularSection(2)));
        modularSectionItems.add(new ModularSectionItem(2, "7191", new ModularSection(2)));
        modularSectionItems.add(new ModularSectionItem(1, "7113", new ModularSection(3)));
        modularSectionItems.add(new ModularSectionItem(2, "7291", new ModularSection(3)));
        modularSectionItems.add(new ModularSectionItem(1, "7114", new ModularSection(4)));
        modularSectionItems.add(new ModularSectionItem(2, "7391", new ModularSection(4)));
        modularSectionItems.add(new ModularSectionItem(1, "7115", new ModularSection(5)));
        modularSectionItems.add(new ModularSectionItem(2, "7491", new ModularSection(5)));
        modularSectionItems.add(new ModularSectionItem(1, "7116", new ModularSection(6)));
        modularSectionItems.add(new ModularSectionItem(2, "7691", new ModularSection(6)));
        modularSectionItems.add(new ModularSectionItem(1, "7117", new ModularSection(7)));
        modularSectionItems.add(new ModularSectionItem(2, "7791", new ModularSection(7)));
        modularSectionItems.add(new ModularSectionItem(1, "7137", new ModularSection(8)));
        modularSectionItems.add(new ModularSectionItem(2, "7119", new ModularSection(2)));
        return  modularSectionItems;
    }

    private static List<Store> createStores() {
        List<Store> stores = new ArrayList<>();

        stores.add(new Store("12345", "US", 100));
        stores.add(new Store("56789", "US", 101));
        stores.add(new Store("13568", "US", 102));
        return stores;
    }

    private static List<Section> createSections() {
        List<Section> sections = new ArrayList<>();

        sections.add(new Section("111111", "0111111", "A1-1", new Store("12345")));
        sections.add(new Section("111112", "0111111", "A1-2", new Store("12345")));
        sections.add(new Section("111113", "0111113", "B1-1", new Store("12345")));
        sections.add(new Section("222222", "0222222", "A2-1", new Store("56789")));
        sections.add(new Section("222223", "0222222", "A2-2", new Store("56789")));
        sections.add(new Section("222224", "0222224", "B2-1", new Store("56789")));
        sections.add(new Section("333333", "0333333", "A3-1", new Store("13568")));
        sections.add(new Section("333334", "0333333", "A3-2", new Store("13568")));
        sections.add(new Section("333335", "0333333", "A3-3", new Store("13568")));
        sections.add(new Section("333336", "0333336", "C1-1", new Store("13568")));
        sections.add(new Section("333337", "0333336", "C1-2", new Store("13568")));
        sections.add(new Section("333338", "0333338", "D1-1", new Store("13568")));
        sections.add(new Section("333339", "0333339", "D1-2", new Store("13568")));
        return sections;
    }

    private static List<Flex> createFlexes() {
        List<Flex> flexes = new ArrayList<>();

        flexes.add(new Flex("7111", new Section("111113"), "John Smith"));
        flexes.add(new Flex("7112", new Section("111111"), "John Smith"));
        flexes.add(new Flex("7113", new Section("111112"), "Jane Doe"));
        flexes.add(new Flex("7114", new Section("222222"), "John Smith"));
        flexes.add(new Flex("7115", new Section("222222"), "John Doe"));
        flexes.add(new Flex("7111", new Section("222223"), "John Smith"));
        flexes.add(new Flex("7112", new Section("222224"), "John Smith"));
        flexes.add(new Flex("7115", new Section("222224"), "System"));
        flexes.add(new Flex("7117", new Section("333333"), "John Smith"));
        flexes.add(new Flex("7118", new Section("333334"), "John Smith"));
        flexes.add(new Flex("7128", new Section("333334"), "Jane Smith"));
        flexes.add(new Flex("7118", new Section("333335"), "Jane Smith"));
        flexes.add(new Flex("7119", new Section("333336"), "John Smith"));
        flexes.add(new Flex("7129", new Section("333336"), "John Smith"));
        flexes.add(new Flex("7139", new Section("333336"), "System"));
        flexes.add(new Flex("7139", new Section("333337"), "John Smith"));
        flexes.add(new Flex("7131", new Section("333338"), "Jane Smith"));
        flexes.add(new Flex("7132", new Section("333338"), "John Smith"));
        flexes.add(new Flex("7133", new Section("333339"), "John Doe"));
        flexes.add(new Flex("7138", new Section("333339"), "Jane Smith"));
        flexes.add(new Flex("7137", new Section("333339"), "John Smith"));

        return flexes;
    }


}
