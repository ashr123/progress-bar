package io.github.ashr123.progress.bar;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Validated
public class WebController {
	@GetMapping(value = "/{progress}", produces = "image/svg+xml")
	public String generateProgress(
			@PathVariable
			@PositiveOrZero
			@Max(100)
			double progress,

			@RequestParam(required = false)
			Optional<String> title,

			@RequestParam(defaultValue = "100")
			@Positive
			double scale,

			@RequestParam(required = false)
			Optional<@Positive Double> width,

			@RequestParam(name = "title-color",
					defaultValue = "#428bca")
			@Pattern(regexp = "^#?(?:(?:[\\da-f]{3}){1,2}|(?:[\\da-f]{4}){1,2})$",
					flags = Pattern.Flag.CASE_INSENSITIVE)
			String titleColor,

			@RequestParam(defaultValue = "%")
			String suffix,

			@RequestParam(name = "maximum-fraction-digits",
					defaultValue = "2")
			@PositiveOrZero
			int maximumFractionDigits,

			Model model
	) {
		model.addAttribute("title", title)
				.addAttribute("titleWidth", title.map(s -> 10 + 6 * s.length()).orElse(0))
				.addAttribute("titleColor", titleColor.charAt(0) == '#' ? titleColor : '#' + titleColor)
				.addAttribute("scale", scale)
				.addAttribute("progress", progress)
				.addAttribute("progressWidth", width.isPresent() ? width.get() : title.isPresent() ? 60 : 90)
				.addAttribute("progressColor", Utils.getProgressColor(progress, scale))
				.addAttribute("suffix", suffix)
				.addAttribute("maximumFractionDigits", maximumFractionDigits);

		//noinspection SpringMVCViewInspection
		return "progress";
	}
}
