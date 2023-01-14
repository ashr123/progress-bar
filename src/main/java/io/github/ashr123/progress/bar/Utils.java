package io.github.ashr123.progress.bar;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component
public class Utils
{
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

	static
	{
		NUMBER_FORMAT.setMaximumFractionDigits(2);
	}

	private Utils()
	{
	}

	public static String formatDecimal(double d)
	{
		return NUMBER_FORMAT.format(d);
	}

	public static double min(double a, double b)
	{
		return Math.min(a, b);
	}

	public static String getProgressColor(double progress, double scale)
	{
		final double ratio = progress / scale;
		return ratio < 1.0 / 3 ? "#d9534f" :
				ratio < 2.0 / 3 ? "#f0ad4e" :
						"#5cb85c";
	}
}
