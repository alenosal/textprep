package com.example.textprep.Exceptions;



import com.example.textprep.Services.SchemeService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public abstract class AppExceptionHandler extends SchemeService {
    @ExceptionHandler(SchemeStorageException.class)
    public ModelAndView handleException(SchemeStorageException exception, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMsg());
        mav.setViewName("error");
        return mav;
    }
}
