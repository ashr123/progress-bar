package io.github.ashr123.progress.bar;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component
public class Utils
{
	private Utils()
	{
	}

	public static String formatDecimal(double d, int maximumFractionDigits)
	{
		final NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(maximumFractionDigits);
		return numberFormat.format(d);
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
