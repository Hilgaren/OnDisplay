/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Document ready function
$(document).ready(function () {
    loadAddresses();

    $('#add-button').click(function (event) {

        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'address',
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                street: $('#add-street').val(),
                city: $('#add-city').val(),
                state: $('#add-state').val(),
                zip: $('#add-zip').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {

            $('#add-first-name').val('');
            $('#add-last-name').val('');
            $('#add-street').val('');
            $('#add-city').val('');
            $('#add-state').val('');
            $('#add-zip').val('');
            loadAddresses();
            $('#validationErrors').empty();

        }).error(function (data, status) {
            // #2 - Go through each of the fieldErrors and display the associated error
            // message in the validationErrors div
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrors').append(validationError.message).append($('<br>'));
                //errorDiv.append(validationError.message).append($('<br>'));
            });

        });

    });

    $('#edit-button').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'PUT',
            url: 'address/' + $('#edit-address-id').val(),
            data: JSON.stringify({
                addressId: $('#edit-address-id').val(),
                firstName: $('#edit-first-name').val(),
                lastName: $('#edit-last-name').val(),
                street: $('#edit-street').val(),
                city: $('#edit-city').val(),
                state: $('#edit-state').val(),
                zip: $('#edit-zip').val()

            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            //  $('#editModal').hide();
            loadAddresses();
            $('#editModal').modal('hide');
            $('#validationErrorsModal').empty();
        }).error(function (data, status) {
            // #2 - Go through each of the fieldErrors and display the associated error
            // message in the validationErrors div
            $('#validationErrorsModal').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrorsModal').append(validationError.message).append($('<br>'));
                //errorDiv.append(validationError.message).append($('<br>'));
            });

        });
    });

    $('#search-button').click(function (event) {
        // we don’t want the button to actually submit
        // we'll handle data submission via ajax
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/addresses',
            data: JSON.stringify({
                firstName: $('#search-first-name').val(),
                lastName: $('#search-last-name').val(),
                street: $('#search-street').val(),
                city: $('#search-city').val(),
                state: $('#search-state').val(),
                zip: $('#search-zip').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-first-name').val(),
                    $('#search-last-name').val(),
                    $('#search-street').val(),
                    $('#search-city').val(),
                    $('#search-state').val(),
                    $('#search-zip').val(),
                    fillAddressTable(data, status);
        });
    });

    $('#edit-modal-cancel').click(function (event) {
        event.preventDefault();
            $('#validationErrorsModal').empty();
        });

});

//==========
// FUNCTIONS
//==========

function loadAddresses() {
    // Make an Ajax GET call to the 'addresss' endpoint. Iterate through
    // each of the JSON objects that are returned and render them to the
    // summary table
    $.ajax({
        url: "addresses"
    }).success(function (data, status) {
        fillAddressTable(data, status);
    });
}

function fillAddressTable(addressTable, status) {
    // clear the previous list
    clearAddressTable();
    // grab the tbody element that will hold the new list of addresss
    var cTable = $('#contentRows');
    // render the new address data to the table
    $.each(addressTable, function (index, address) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(address.firstName)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the address name
                .append($('<td>').text(address.lastName))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'

                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteAddress(' +
                                            address.addressId + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Delete
                ); // ends the <tr> for this Address
    }); // ends the 'each' function
}

function deleteAddress(id) {
    var answer = confirm("Do you really want to delete this address?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'address/' + id
        }).success(function () {
            loadAddresses();
        });
    }
}

// Clear all content rows from the summary table
function clearAddressTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#address-firstName').text(address.firstName);
        modal.find('#address-lastName').text(address.lastName);
        modal.find('#address-street').text(address.street);
        modal.find('#address-city').text(address.city);
        modal.find('#address-state').text(address.state);
        modal.find('#address-zip').text(address.zip);
    });

});

$('#editModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + addressId

    }).success(function (address) {

        modal.find('#address-id').text(address.addressId);
        modal.find('#edit-address-id').val(address.addressId);
        modal.find('#edit-first-name').val(address.firstName);
        modal.find('#edit-last-name').val(address.lastName);
        modal.find('#edit-street').val(address.street);
        modal.find('#edit-city').val(address.city);
        modal.find('#edit-state').val(address.state);
        modal.find('#edit-zip').val(address.zip);
    });
});
