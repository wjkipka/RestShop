package com.roche.application.rest;

import com.roche.application.rest.data.request.Product;
import com.roche.application.rest.data.response.ErrorContainer;
import com.roche.application.rest.data.response.ProductsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST end point interface for the product data.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@Path("/product")
@Schema(description = "Endpoint for the product specific operations")
public interface ProductEndPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delivers all products",
            tags = "frontend",
            description = "All product data found in the database will be delivered here.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of the products",
                            content = @Content(schema = @Schema(implementation = ProductsResponse.class)
                            )),

                    @ApiResponse(responseCode = "412", description = "Input validation failed: general error found",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "503", description = "Database error",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
            })
    Response getAllProducts();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates new product",
            description = "Creates new product in the database.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful creation of product entity",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Any errors occurred during processing",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "412", description = "Input validation failed: general error found",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "503", description = "Database error",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
            })
    Response createProduct(
            @Parameter(description = "The new product.", required = true, schema = @Schema(implementation = Product.class))
            @Valid @NotNull Product product, @Context UriInfo uriInfo);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a product",
            description = "Updates an existing product in the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful update of product entity",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Any errors occurred during processing",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "412", description = "Input validation failed: general error found",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "503", description = "Database error",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
            })
    Response updateProduct(
            @Parameter(description = "The updated product.", required = true, schema = @Schema(implementation = Product.class))
            @Valid @NotNull Product product);

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletes a product",
            description = "Removes an existing product from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful removal of product entity"),
                    @ApiResponse(responseCode = "400", description = "Any errors occurred during processing",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "412", description = "Input validation failed: general error found",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class))),
                    @ApiResponse(responseCode = "503", description = "Database error",
                            content = @Content(schema = @Schema(implementation = ErrorContainer.class)))
            })
    Response deleteProduct(
            @Parameter(description = "The product to delete.", required = true, schema = @Schema(implementation = Product.class))
            @Valid @NotNull Product product);

}
