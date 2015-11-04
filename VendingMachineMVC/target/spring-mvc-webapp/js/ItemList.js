/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadItems();
    updateDeposit(0, status);
    $('#add-moneh-button').click(function (event) {
        event.preventDefault();
        if ($('#add-moneh').val() > 0) {
            $.ajax({
                type: 'PUT',
                url: 'money/',
                data: JSON.stringify({
                    deposit: $('#add-moneh').val()
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json'
            }).success(function (data, status) {
                $('#add-moneh').val('0.00');
                updateDeposit(data, status);
                loadItems();
            }
            ).error(function (data, status) {
                $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                    $('#validationErrors').empty();
                    $('#validationErrors').append(validationError.message).append($('<br>'));

                });
            });
        }
        else {
            $('#add-moneh').val('0.00');
        }
    });

    $('#dispense').click(function (event) {
        event.preventDefault();
        dispenseDeposit();
    });

    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'item',
            data: JSON.stringify({
                name: $('#add-item-name').val(),
                quantity: $('#add-quantity').val(),
                pennyCost: $('#add-cost').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#add-item-name').val('');
            $('#add-quantity').val('');
            $('#add-cost').val('');
            $('#validationErrors').empty();
            $('#change').empty();
            loadItems();

        }).error(function (data, status) {

            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrors').empty();
                $('#change').empty();
                $('#validationErrors').append(validationError.message).append($('<br>'));

            });
        });
    });
});



function loadItems() {

    $.ajax({
        url: "items"
    }).success(function (data, status) {

        fillItemTable(data, status);

        fillAdminTable(data, status);
    });
}

function updateDeposit(data, status) {
    $.ajax({
        type: 'GET',
        url: "money"
    }).success(function (data, status) {
        $('#deposit').text(data / 100);
    });

}

function dispenseDeposit() {

    var change = $('#deposit').text();
    $.ajax({
        type: 'PATCH',
        url: 'money/'
    }).success(function (data, status) {
        $('#validationErrors').empty();
                $('#change').empty();
        $('#change').append('Change dispensed! Have a great day!' + ' ' + 'Change: $' + change).append($('<br>'));

        updateDeposit(data, status);
        updateDeposit(0, status);
    });




}

function fillItemTable(itemTable, status) {

    clearItemTable();

    var cTable = $('#contentRows');

    $.each(itemTable, function (index, item) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<p>')
                                .attr({
                                    'data-item-id': item.itemId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(item.name)
                                )
                        )
                .append($('<td>').text(item.quantity))
                .append($('<td>')
                        .append($('<p>')
                                .attr({
                                    'data-item-id': item.itemId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text(item.pennyCost)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>').append($('<a>').attr({
                    'onClick': 'buyItem(' +
                            item.itemId + ')'
                }).text('Buy' + ' ' + item.name))
                        )
                );
    });
}

function fillAdminTable(itemTable, status) {

    clearAdminTable();

    var cTable = $('#adminContentRows');

    $.each(itemTable, function (index, item) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<p>')
                                .attr({
                                    'data-item-id': item.itemId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(item.name)
                                )
                        )
                .append($('<td>').text(item.quantity))
                .append($('<td>')
                        .append($('<p>')
                                .attr({
                                    'data-item-id': item.itemId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text(item.pennyCost)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>').append($('<a>').attr({
                    'onClick': 'buyItem(' +
                            item.itemId + ')'
                }).text('Buy' + ' ' + item.name))
                        ).append($('<td>')
                .append($('<a>')
                        .attr({
                            'onClick': 'deleteItem(' +
                                    item.itemId + ')'
                        })
                        .text('Delete')
                        )
                )
                );
    });
}

function deleteItem(id) {
    var answer = confirm("Do you really want to delete this Item?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'item/' + id
        }).success(function () {
            loadItems();

        });
    }
}

function buyItem(id) {
    var answer = confirm("Do you really want to buy this Item?");
    if (answer === true) {
        $.ajax({
            type: 'PATCH',
            url: 'item/' + id
        }).success(function (data, status) {
            $('#validationErrors').empty();
            $('#change').empty();
            loadItems();
            var response = data;
            $('#validationErrors').append(response).append($('<br>'));
            updateDeposit(0, status);
        });
    }
}

function clearItemTable() {
    $('#contentRows').empty();
}

function clearItemTable() {
    $('#contentRows').empty();
}

function clearAdminTable() {
    $('#adminContentRows').empty();
}