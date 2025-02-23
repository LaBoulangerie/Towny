package com.palmergames.bukkit.util;

import com.palmergames.bukkit.towny.utils.TownyComponents;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.Nullable;

import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.util.StringMgmt;

import net.kyori.adventure.text.format.NamedTextColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colors {

	private static final Map<String, String> legacyLookupMap = new HashMap<>();
	private static final Pattern legacyPattern = Pattern.compile("[§&][0-9a-fk-or]");

	/*
	 * Legacy colors
	 */
	
	public static final String Black = "\u00A70";
	public static final String Navy = "\u00A71";
	public static final String Green = "\u00A72";
	public static final String Blue = "\u00A73";
	public static final String Red = "\u00A74";
	public static final String Purple = "\u00A75";
	public static final String Gold = "\u00A76";
	public static final String LightGray = "\u00A77";
	public static final String Gray = "\u00A78";
	public static final String DarkPurple = "\u00A79";
	public static final String LightGreen = "\u00A7a";
	public static final String LightBlue = "\u00A7b";
	public static final String Rose = "\u00A7c";
	public static final String LightPurple = "\u00A7d";
	public static final String Yellow = "\u00A7e";
	public static final String White = "\u00A7f";

	/*
	 * Minimessage colors 
	 */
	
	public static final String DARK_RED = "<dark_red>";
	public static final String RED = "<red>";
	public static final String GOLD = "<gold>";
	public static final String YELLOW = "<yellow>";
	public static final String DARK_GREEN = "<dark_green>";
	public static final String GREEN = "<green>";
	public static final String DARK_AQUA = "<dark_aqua>";
	public static final String AQUA = "<aqua>";
	public static final String DARK_BLUE = "<dark_blue>";
	public static final String BLUE = "<blue>";
	public static final String LIGHT_PURPLE = "<light_purple>";
	public static final String DARK_PURPLE = "<dark_purple>";
	public static final String WHITE = "<white>";
	public static final String GRAY = "<gray>";
	public static final String DARK_GRAY = "<dark_gray>";
	public static final String BLACK = "<black>";

	public static String strip(String line) {
		return TownyComponents.stripTags(ChatColor.stripColor(line));
	}

	public static String translateColorCodes(String str) {
		return StringMgmt.translateHexColors(ChatColor.translateAlternateColorCodes('&', str));
	}

	public static String translateLegacyCharacters(String input) {
		final Matcher matcher = legacyPattern.matcher(input);

		while (matcher.find()) {
			String legacy = matcher.group();
			input = input.replace(legacy, legacyLookupMap.getOrDefault(legacy.substring(1), legacy));
		}

		return input;
	}

	private static final Function<String, String> modernHexFunction = (hex) -> "<#" + hex + ">";

	/**
	 * Converts non-minimessage hex formats to minimessage.
	 * @param input The input that may or may not contain hex.
	 * @return The input, with the minimessage hex format.
	 */
	public static String translateLegacyHex(String input) {
		return StringMgmt.translateHexColors(input, modernHexFunction);
	}

	/**
	 * @param colorCode A legacy or MiniMessage color code.
	 * @return the {@link NamedTextColor} for the entered color string, or null if it is invalid.
	 */
	@Nullable
	public static NamedTextColor toNamedTextColor(String colorCode) {
		return switch (colorCode) {
			case "\u00A70", BLACK -> NamedTextColor.BLACK;
			case "\u00A71", DARK_BLUE -> NamedTextColor.DARK_BLUE;
			case "\u00A72", DARK_GREEN -> NamedTextColor.DARK_GREEN;
			case "\u00A73", DARK_AQUA -> NamedTextColor.DARK_AQUA;
			case "\u00A74", DARK_RED -> NamedTextColor.DARK_RED;
			case "\u00A75", DARK_PURPLE -> NamedTextColor.DARK_PURPLE;
			case "\u00A76", GOLD -> NamedTextColor.GOLD;
			case "\u00A77", GRAY -> NamedTextColor.GRAY;
			case "\u00A78", DARK_GRAY -> NamedTextColor.DARK_GRAY;
			case "\u00A79", BLUE -> NamedTextColor.BLUE;
			case "\u00A7a", GREEN -> NamedTextColor.GREEN;
			case "\u00A7b", AQUA -> NamedTextColor.AQUA;
			case "\u00A7c", RED -> NamedTextColor.RED;
			case "\u00A7d", LIGHT_PURPLE -> NamedTextColor.LIGHT_PURPLE;
			case "\u00A7e", YELLOW -> NamedTextColor.YELLOW;
			case "\u00A7f", WHITE -> NamedTextColor.WHITE;
			default -> null;
		};
	}

	public static String colorTown(String townName) {
		return translateColorCodes(getTownColor() + townName);
	}

	public static String colorTown(Town town) {
		return translateColorCodes(getTownColor() + town);
	}

	public static String colorNation(String nationName) {
		return translateColorCodes(getNationColor() + nationName);
	}

	public static String colorNation(Nation nation) {
		return translateColorCodes(getNationColor() + nation);
	}

	public static String getTownColor() {
		return TownySettings.getPAPIFormattingMayor();
	}

	public static String getNationColor() {
		return TownySettings.getPAPIFormattingKing();
	}

	static {
		legacyLookupMap.put("4", DARK_RED);
		legacyLookupMap.put("c", RED);
		legacyLookupMap.put("6", GOLD);
		legacyLookupMap.put("e", YELLOW);
		legacyLookupMap.put("2", DARK_GREEN);
		legacyLookupMap.put("a", GREEN);
		legacyLookupMap.put("3", DARK_AQUA);
		legacyLookupMap.put("b", AQUA);
		legacyLookupMap.put("1", DARK_BLUE);
		legacyLookupMap.put("9", BLUE);
		legacyLookupMap.put("d", LIGHT_PURPLE);
		legacyLookupMap.put("5", DARK_PURPLE);
		legacyLookupMap.put("f", WHITE);
		legacyLookupMap.put("7", GRAY);
		legacyLookupMap.put("8", DARK_GRAY);
		legacyLookupMap.put("0", BLACK);

		legacyLookupMap.put("k", "<obfuscated>");
		legacyLookupMap.put("l", "<bold>");
		legacyLookupMap.put("m", "<strikethrough>");
		legacyLookupMap.put("n", "<underline>");
		legacyLookupMap.put("o", "<italic>");
		legacyLookupMap.put("r", "<reset>");
	}
}
