package crdev.finance_focus.controller;

import crdev.finance_focus.dto.CategoryDto;
import crdev.finance_focus.dto.ExpenseDto;
import crdev.finance_focus.dto.ResponseMessageAPI;
import crdev.finance_focus.entity.Category;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import crdev.finance_focus.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "API категорий для транзакции")
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все категории для расходов",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение списка категорий(Для расходов)",
            description = "Возвращает список категорий в формате json."
    )
    @GetMapping("/expense")
    ResponseMessageAPI<List<CategoryDto>> getAllExpenseCategory(){
        try { return new ResponseMessageAPI<>(
                service.getAllExpenseCategory(),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("CategoryController: getAllExpenseCategory() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка категорий для расходов",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все категории для доходов",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
                    }
            )
    }
    )
    @Operation(
            summary = "Получение списка категорий(Для доходов)",
            description = "Возвращает список категорий в формате json."
    )
    @GetMapping("/income")
    ResponseMessageAPI<List<CategoryDto>> getAllIncomeCategory(){
        try { return new ResponseMessageAPI<>(
                service.getAllIncomeCategory(),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("CategoryController: getAllIncomeCategory() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка категорий для доходов",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Категория успешно добавлена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    }
            )
    }
    )
    @Operation(
            summary = "Создание категории",
            description = "Возвращает созданную категорию для расходов/доходов в формате json."
    )
    @PostMapping()
    ResponseMessageAPI<Category> create (@RequestBody CategoryDto model){
        try { return new ResponseMessageAPI<>(
                service.create(model),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            System.out.printf("CategoryController: getAll() %s%n", e);
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION, e.getClass().getSimpleName(),
                    "Ошибка при получении списка категорий",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}
