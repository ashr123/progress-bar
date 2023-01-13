package io.github.ashr123.progress.bar;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Validated
public class WebController
{
	private static String getProgressColor(double progress, double scale)
	{
		final double ratio = progress / scale;
		return ratio < 1.0 / 3 ? "#d9534f" :
				ratio < 2.0 / 3 ? "#f0ad4e" :
						"#5cb85c";
	}

	@RequestMapping(value = "/")
	public String generateProgress(@RequestParam @PositiveOrZero double progress,
								   @RequestParam(required = false) Optional<String> title,
								   @RequestParam(defaultValue = "100") @Positive double scale,
								   @RequestParam(required = false) Optional<Double> width,
								   @RequestParam(defaultValue = "428bca") String color,
								   @RequestParam(defaultValue = "%") String suffix,
								   Model model)
	{
		model.addAttribute("title", title)
				.addAttribute("title_width", title.map(s -> 10 + 6 * s.length()).orElse(0))
				.addAttribute("title_color", color)
				.addAttribute("scale", scale)
				.addAttribute("progress", progress)
				.addAttribute("progress_width", width.isPresent() ? width.get() : title.isPresent() ? 60 : 90)
				.addAttribute("progress_color", getProgressColor(progress, scale))
				.addAttribute("suffix", suffix);

		return "progress";
	}
}
